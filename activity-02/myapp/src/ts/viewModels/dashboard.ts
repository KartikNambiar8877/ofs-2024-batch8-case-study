// JavaScript
import * as AccUtils from "../accUtils";
import * as ko from "knockout";
import * as Bootstrap from "ojs/ojbootstrap";
import "oj-c/input-text";
import "ojs/ojinputnumber";
import "ojs/ojknockout";
import "ojs/ojformlayout";
import "ojs/ojinputtext";
import "ojs/ojlabel";
import "ojs/ojlabelvalue";
import { ojButton } from "ojs/ojbutton";
import "ojs/ojbutton";
import { RESTDataProvider } from 'ojs/ojrestdataprovider';

class DashboardViewModel {
  activatedButton: ko.Observable<string>;
  
  public buttonAction = (event: ojButton.ojAction) => {
    this.activatedButton((event.currentTarget as HTMLElement).id);
    return true;
  };
  value: ko.Observable<string>;
  firstname: ko.Observable<string | null>;

  constructor() {
    this.activatedButton = ko.observable("(None activated yet)");
    this.value = ko.observable(""); // password
    this.firstname = ko.observable(null);
  }
  handleSubmit = () => {
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
    .then(response => response.json())
    .then(result => {
      if (result.success) {
        alert('Login successful');
        // Redirect or handle successful login here
      } else {
        alert('Login failed');
        // Handle failed login here
      }
    })
    .catch(error => {
      console.error('Error:', error);
      alert('An error occurred');
    });
  };

}


export = DashboardViewModel;



