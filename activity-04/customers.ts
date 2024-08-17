import * as AccUtils from "../accUtils";
import 'ojs/ojknockout';
import 'ojs/ojtable';
import { RESTDataProvider } from 'ojs/ojrestdataprovider';
type D = {"customerId":number,"fName":string,"lName":string}
type K = D["customerId"]
class IncidentsViewModel {
  dataprovider : RESTDataProvider<K,D>;
  constructor(){
    this.dataprovider= new RESTDataProvider({
      keyAttributes:"customerId",
      url:'http://localhost:8080/customer/allcustomers',
      transforms:{
        fetchFirst:{
          request:async (options)=>{
            console.log(options);
            const url = new URL(options.url);
            return new  Request(url.href);
          },
          response:async({body})=>{
            let data = body;
            return {data}
          }
        }
      }
    })
  }
}
export = IncidentsViewModel;