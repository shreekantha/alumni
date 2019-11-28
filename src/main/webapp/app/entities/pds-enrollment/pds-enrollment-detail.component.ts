import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPdsEnrollment } from 'app/shared/model/pds-enrollment.model';

@Component({
  selector: 'jhi-pds-enrollment-detail',
  templateUrl: './pds-enrollment-detail.component.html'
})
export class PdsEnrollmentDetailComponent implements OnInit {
  pdsEnrollment: IPdsEnrollment;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pdsEnrollment }) => {
      this.pdsEnrollment = pdsEnrollment;
    });
  }

  previousState() {
    window.history.back();
  }
}
