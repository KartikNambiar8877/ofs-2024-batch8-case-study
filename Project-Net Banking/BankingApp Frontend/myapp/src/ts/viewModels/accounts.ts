import * as AccUtils from "../accUtils";
import * as ko from "knockout";
import 'ojs/ojknockout';
import 'ojs/ojtable';
import 'ojs/ojinputtext';
import 'ojs/ojinputnumber';
import 'ojs/ojformlayout';
import { ojButton } from 'ojs/ojbutton';
import 'ojs/ojbutton';
import { RESTDataProvider } from 'ojs/ojrestdataprovider';

type Account = {
  accountNo: number;
  accountType: string;
  minBalance: number;
  mainBalance: number;
  customerID: number;
  overdraftBalance: number;
};

type K = Account["accountNo"];

class AccountsViewModel {
  AccountType: ko.Observable<string>;
  Validity: ko.Observable<number>;
  AccountNo: ko.Observable<number>;
  activatedButton: ko.Observable<string>;
  dataprovider: RESTDataProvider<K, Account>;

  constructor() {
    this.AccountType = ko.observable("");
    this.Validity = ko.observable(0);
    this.AccountNo = ko.observable(0); // Observable for account number to delete
    this.activatedButton = ko.observable("(None activated yet)");
    
   
    const customerID = sessionStorage.getItem('customerId');
    
    if (!customerID) {
      throw new Error('Customer ID not found in local storage');
    }

    const url = `http://localhost:8080/account/listaccounts/${customerID}`;

    this.dataprovider = new RESTDataProvider({
      keyAttributes: "accountNo",
      url: url,
      transforms: {
        fetchFirst: {
          request: async (options) => {
            const url = new URL(options.url);
            return new Request(url.href);
          },
          response: async ({ body }) => {
            let data = body;
            return { data };
          }
        }
      }
    });
  }

  public buttonAction2 = (event: ojButton.ojAction) => {
    this.activatedButton((event.currentTarget as HTMLElement).id);

    const accountType = this.AccountType();
    const validity = this.Validity();
    const customerID = sessionStorage.getItem('customerId');
    const data = {
      customerID: customerID,
      accountType: accountType,
      validity: validity
    };

    console.log(data);

    fetch('http://localhost:8080/account/addaccount', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
      console.log(result);
      alert('Account added successfully');
      this.dataprovider.refresh();
    })
    .catch(error => {
      console.error('Error:', error);
      alert('An error occurred while adding the account');
    });

    return true;
  };

  public buttonAction3 = (event: ojButton.ojAction) => {
    this.activatedButton((event.currentTarget as HTMLElement).id);

    const accountNo = this.AccountNo();

    if (!accountNo) {
      alert('Please enter an account number');
      return;
    }
    const data = {
      accountNo : accountNo,
    };

    fetch(`http://localhost:8080/account/delete`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      if (response.ok) {
        alert('Account deleted successfully');
        // Optionally, refresh the data provider to reflect the changes
         this.dataprovider.refresh();
      } else {
        return response.json().then(errorData => {
          throw new Error(errorData.message || 'Failed to delete account');
        });
      }
    })
    .catch(error => {
      console.error('Error:', error);
      alert('An error occurred while deleting the account');
    });

    return true;
  };
}

export = AccountsViewModel;
