import * as ko from "knockout";
import 'ojs/ojknockout';
import 'ojs/ojtable';
import 'ojs/ojinputtext';
import "oj-c/input-text";
import "oj-c/input-number";
import 'ojs/ojinputnumber';
import 'ojs/ojbutton';
import { ojButton } from 'ojs/ojbutton';
import { RESTDataProvider } from 'ojs/ojrestdataprovider';

type Transaction = {
  transactionId: number;
  senderAccount: { accountNo: number };
  receiverAccount: { accountNo: number };
  transactionAmount: number;
  transactionType: string;
  transactionDatetime: string;
};

type T = Transaction["transactionId"];

class TransactionViewModel {
  accountNo: ko.Observable<number>;
  receiverAccountNo: ko.Observable<number>;
  Amount: ko.Observable<number>;
  transactionDataProvider: ko.Observable<RESTDataProvider<T, Transaction> | null>;
  activatedButton: ko.Observable<string>;

  constructor() {
    this.accountNo = ko.observable(0);
    this.receiverAccountNo = ko.observable(0);
    this.Amount = ko.observable(0);
    this.activatedButton = ko.observable("(None activated yet)");
    this.transactionDataProvider = ko.observable<RESTDataProvider<T, Transaction> | null>(null);
  }

  public fetchTransactionHistory = (event: ojButton.ojAction) => {
    if (!this.accountNo()) {
      alert('Please enter an account number');
      return;
    }

    const url = `http://localhost:8080/transactions/transactionhistory`;

    const dataProvider = new RESTDataProvider<T, Transaction>({
      keyAttributes: "transactionId",
      url: url,
      transforms: {
        fetchFirst: {
          request: async (options) => {
            const requestUrl = new URL(options.url);
            return new Request(requestUrl.href, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify({ accountNo: this.accountNo() })
            });
          },
          response: async ({ body }) => {
            let data = body;
            return { data };
          }
        }
      }
    });

    this.transactionDataProvider(dataProvider);
  };

  public transferFunds = (event: ojButton.ojAction) => {
    if (!this.accountNo()) {
      alert('Please enter your account number');
      return;
    }
    if (!this.receiverAccountNo() || !this.Amount()) {
      alert('Please enter the receiver\'s account number and amount');
      return;
    }

    const transferData = {
      senderAccount: {
        accountNo: this.accountNo()
      },
      receiverAccount: {
        accountNo: this.receiverAccountNo()
      },
      transactionAmount: this.Amount()
    };

    const url = `http://localhost:8080/transactions/transfer`;

    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(transferData)
    })
    .then(response => response.json())
    .then(result => {
      console.log(result)
      
      alert('Funds transferred successfully');
      this.fetchTransactionHistory(event); // Optionally refresh transaction history
    })
    .catch(error => {
      console.error('Error:', error);
      alert('An error occurred during the transfer');
    });
};
}

export = TransactionViewModel;
