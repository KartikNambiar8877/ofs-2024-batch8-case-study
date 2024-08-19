import * as ko from "knockout";
import "ojs/ojknockout";
import "ojs/ojformlayout";
import "oj-c/input-text";
import "oj-c/input-password";
import "ojs/ojinputnumber";
import "oj-c/input-number";
import { ojButton } from "ojs/ojbutton";
import "ojs/ojbutton";

class CustomerViewModel {
  firstname: ko.Observable<string>;
  lastname: ko.Observable<string>;
  username: ko.Observable<string>;
  password: ko.Observable<string>;
  addressLine1: ko.Observable<string>;
  addressLine2: ko.Observable<string>;
  addressLine3: ko.Observable<string>;
  city: ko.Observable<string>;
  state: ko.Observable<string>;
  zip: ko.Observable<number>;
  phone: ko.Observable<number>;
  email: ko.Observable<string>;
  activatedButton: ko.Observable<string>;

  constructor() {
    this.firstname = ko.observable("");
    this.lastname = ko.observable("");
    this.username = ko.observable("");
    this.password = ko.observable("");
    this.addressLine1 = ko.observable("");
    this.addressLine2 = ko.observable("");
    this.addressLine3 = ko.observable("");
    this.city = ko.observable("");
    this.state = ko.observable("");
    this.zip = ko.observable(0);
    this.phone = ko.observable(0);
    this.email = ko.observable("");
    this.activatedButton = ko.observable("(None activated yet)");
  }

  public buttonAction = (event: ojButton.ojAction) => {
    this.activatedButton((event.currentTarget as HTMLElement).id);
    
    const data = {
      fName: this.firstname(),
      lName: this.lastname(),
      username: this.username(),
      password: this.password(),
      addressLine1: this.addressLine1(),
      addressLine2: this.addressLine2(),
      addressLine3: this.addressLine3(),
      city: this.city(),
      state: this.state(),
      zip: this.zip(),
      phone: this.phone(),
      email: this.email()
    };

    console.log("Data being sent:", data);

    fetch('http://localhost:8080/customer/addcustomer', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      if (response.ok) {
        return response.json(); 
      } else {
        return response.json().then(err => { throw err; });
      }
    })
    .then(result => {
      console.log("Server response:", result);
      alert('Customer added successfully');
      window.location.href = "?ojr=login";
    })
    .catch(error => {
      console.error('Error:', error);
      alert('An error occurred while adding the customer');
    });

    return true;
  };
}

export = CustomerViewModel;
