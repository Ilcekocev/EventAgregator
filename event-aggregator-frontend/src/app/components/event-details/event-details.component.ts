import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../model/Event";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  @Input()
  event: Event;

  showEditMenu: boolean;

  constructor() { }

  ngOnInit() {
    this.showEditMenu = false;
  }

  public openOrCloseEditMenu() {
    this.showEditMenu = !this.showEditMenu;
  }

}
