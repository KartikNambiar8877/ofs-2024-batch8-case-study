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
import { RESTDataProvider } from 'ojs/ojrestdataprovider';

class LoginViewModel {
  value: ko.Observable<string>;
  firstname: ko.Observable<string | null>;
  activatedButton: ko.Observable<string>;

  constructor() {
    this.value = ko.observable(""); 
    this.firstname = ko.observable(null);
    this.activatedButton = ko.observable("(None activated yet)");
  }

  public buttonAction = (event: ojButton.ojAction) => {
    this.activatedButton((event.currentTarget as HTMLElement).id);
    const username = this.firstname();
    const password = this.value();

    console.log(username, password);

    const data = {
        username: username,
        password: password
    };
    console.log(data);
    console.log(JSON.stringify(data));

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
        console.log(result);
        alert('Login successful');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred during login');
    });

    return true;
};


}
export = LoginViewModel;
