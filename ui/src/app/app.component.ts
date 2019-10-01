import { Component, OnInit} from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http'; 

imports:[HttpClientModule,  HttpClient]

class Item{
    purchase: string;
    done: boolean;
    price: number;
     
    constructor(purchase: string, price: number) {
  
        this.purchase = purchase;
        this.price = price;
        this.done = false;
    }
}

class User{
    name: string;
    last: string;

    constructor(name: string, last: string) {
        this.name = name;
        this.last = last;
    }
}
 
@Component({
    selector: 'search-app',
    template: `<div class="page-header">
        <h1> Users </h1>
    </div>
    <div class="panel">
        <div class="form-inline">
            <div class="form-group">
                <div class="col-md-8">
                    <input class="form-control" [(ngModel)]="name" placeholder = "name or last name" />
                </div>
            </div>            
            <div class="form-group">
            <div class="col-md-offset-2 col-md-8">
                <button class="btn btn-default" (click)="searchItem(name)">Search</button>
            </div>
        </div>            
        <div class="form-group">
        <div class="col-md-offset-2 col-md-8">
            <button class="btn btn-default" (click)="showAll(name)">Show all</button>
        </div>
    </div>            
</div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>name</th>
                    <th>last name</th>
                </tr>
            </thead>
            <tbody>
                <tr *ngFor="let user of users">
                    <td>{{user.name}}</td>
                    <td>{{user.last}}</td>
                </tr>
            </tbody>
        </table>
    </div>`
})

export class AppComponent { 
    users: User[];// = [];

    searchItem(name: string): void {         
        if(name==null || name.trim()==""){
            this.httpClient.get('http://testapi.us-west-2.elasticbeanstalk.com/service/api/user/v1/').subscribe((data:User[]) => this.users=data);        
            return;
        }
        // this.users.push(new User(name, last));
        this.httpClient.get('http://testapi.us-west-2.elasticbeanstalk.com/service/api/user/v1/?search=' + name).subscribe((data:User[]) => this.users=data);        

    }

    showAll(name: string): void {         
            this.httpClient.get('http://testapi.us-west-2.elasticbeanstalk.com/service/api/user/v1/').subscribe((data:User[]) => this.users=data);        
    }
    constructor(private httpClient: HttpClient){}

    ngOnInit(){          
        this.httpClient.get('http://testapi.us-west-2.elasticbeanstalk.com/service/api/user/v1/').subscribe((data:User[]) => this.users=data);        
    }
}