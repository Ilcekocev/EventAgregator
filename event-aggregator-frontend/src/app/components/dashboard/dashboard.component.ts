import { Component, OnInit } from '@angular/core';
import {Event} from "../../model/Event";
import * as moment from "moment";
import {User} from "../../model/User";
import {UserService} from "../../services/user.service";
import {EventService} from "../../services/event.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  user: User;
  event: Event;

  constructor(private userService: UserService,
              private eventService: EventService) { }

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    this.userService.getUser()
      .subscribe(data => {
        this.user = data;
        console.log("Dashbooard - Printing user {}", this.user);
        localStorage.setItem('id', data.id);
      })
  }

}
