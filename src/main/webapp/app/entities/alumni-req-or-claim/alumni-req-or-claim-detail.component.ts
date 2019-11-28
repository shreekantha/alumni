import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';

@Component({
  selector: 'jhi-alumni-req-or-claim-detail',
  templateUrl: './alumni-req-or-claim-detail.component.html'
})
export class AlumniReqOrClaimDetailComponent implements OnInit {
  alumniReqOrClaim: IAlumniReqOrClaim;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alumniReqOrClaim }) => {
      this.alumniReqOrClaim = alumniReqOrClaim;
    });
  }

  previousState() {
    window.history.back();
  }
}
