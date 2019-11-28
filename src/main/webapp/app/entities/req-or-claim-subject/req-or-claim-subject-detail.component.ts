import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';

@Component({
  selector: 'jhi-req-or-claim-subject-detail',
  templateUrl: './req-or-claim-subject-detail.component.html'
})
export class ReqOrClaimSubjectDetailComponent implements OnInit {
  reqOrClaimSubject: IReqOrClaimSubject;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ reqOrClaimSubject }) => {
      this.reqOrClaimSubject = reqOrClaimSubject;
    });
  }

  previousState() {
    window.history.back();
  }
}
