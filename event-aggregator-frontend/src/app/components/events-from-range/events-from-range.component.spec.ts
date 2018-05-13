import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsFromRangeComponent } from './events-from-range.component';

describe('EventsFromRangeComponent', () => {
  let component: EventsFromRangeComponent;
  let fixture: ComponentFixture<EventsFromRangeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsFromRangeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventsFromRangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
