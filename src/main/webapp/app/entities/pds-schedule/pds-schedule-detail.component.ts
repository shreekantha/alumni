import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPdsSchedule } from 'app/shared/model/pds-schedule.model';

@Component({
  selector: 'jhi-pds-schedule-detail',
  templateUrl: './pds-schedule-detail.component.html'
})
export class PdsScheduleDetailComponent implements OnInit {
  pdsSchedule: IPdsSchedule;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pdsSchedule }) => {
      this.pdsSchedule = pdsSchedule;
    });
  }

  previousState() {
    window.history.back();
  }
}
