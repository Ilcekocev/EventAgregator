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

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    this.userService.getAuthentication()
      .subscribe(data => {
        console.log("Printing auth: {}", data);
      });
    this.userService.getUser()
      .subscribe(data => {
        console.log("Printing user {}", data);
        this.user = data;
        console.log(this.user);
        localStorage.setItem('email', data.email);
      })
  }
}
