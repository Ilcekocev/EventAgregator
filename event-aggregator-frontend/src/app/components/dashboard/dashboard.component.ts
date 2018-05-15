import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  thisWeekToggle: boolean;
  eventsFromRangeToggle: boolean;
  createEventToggle: boolean;

  constructor() { }

  ngOnInit() {
    this.thisWeekToggle = false;
    this.eventsFromRangeToggle = false;
    this.createEventToggle = false;
  }

}
