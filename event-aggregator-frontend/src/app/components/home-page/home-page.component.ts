import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private userService: UserService) { }

  user: any;
  authenticated: boolean;

  ngOnInit() {
    this.getUser();
    this.authenticated = this.user != "N/A";
  }

  getUser() {
    this.userService.getAuthenticationObject()
      .subscribe(data => {
        console.log("Printing user: {}", data);
        this.user = data;
      });
    this.userService.getUser()
      .subscribe(data => {
        console.log(data);
        localStorage.setItem('email', data.email);
      })
  }
}
