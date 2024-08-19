// JavaScript
import * as AccUtils from "../accUtils";
import * as ko from "knockout";
import * as Bootstrap from "ojs/ojbootstrap";
import "oj-c/input-text";
import "oj-c/input-password";
import "ojs/ojinputnumber";
import "ojs/ojknockout";
import "ojs/ojformlayout";
import "ojs/ojinputtext";
import "ojs/ojlabel";
import "ojs/ojlabelvalue";
import { ojButton } from "ojs/ojbutton";
import "ojs/ojbutton";
import * as Router from "ojs/ojrouter";
import { RESTDataProvider } from 'ojs/ojrestdataprovider';
import CoreRouter = require("@oracle/oraclejet/ojcorerouter");
import ModuleRouterAdapter = require("@oracle/oraclejet/ojmodulerouter-adapter");

class LoginViewModel {
  value: ko.Observable<string>;
  firstname: ko.Observable<string | null>;
  activatedButton: ko.Observable<string>;
  router: CoreRouter;

  constructor(args: ModuleRouterAdapter.ViewModelParameters<{label:string}, {}>) {
    this.value = ko.observable(""); 
    this.firstname = ko.observable(null);
    this.activatedButton = ko.observable("(None activated yet)");
    this.router = args.parentRouter;

  }

  public buttonAction = (event: ojButton.ojAction) => {
    this.activatedButton((event.currentTarget as HTMLElement).id);
    const username = this.firstname();
    const password = this.value();
  
    const data = {
      username: username,
      password: password
    };
  
    fetch('http://localhost:8080/customer/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      if (response.ok) {
        return response.json(); 
      } else if (response.status === 403) {
        alert("Account is blocked");
        throw new Error('Account is blocked');
      } else if (response.status === 401) {
        alert("Invalid credentials");
        throw new Error('Invalid credentials');
      } else {
        throw new Error('Login failed');
      }
    })
    .then(result => {
      if (result && result.customerId) {
        sessionStorage.setItem('customerId', result.customerId);
        sessionStorage.setItem('username',result.username);
        console.log('Customer ID stored:', result.customerId);
        alert('Login successful');
        
        this.router.go({path:'accounts'});
        window.location.reload();
      } else {
        alert('Customer ID not found in response');
      }
    })
    .catch(error => {
      console.error('Error:', error);
      alert('An error occurred during login');
    });
  
    return true;
  };
  

}
export = LoginViewModel;
