import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFundraiser } from 'app/shared/model/fundraiser.model';

@Component({
  selector: 'jhi-fundraiser-detail',
  templateUrl: './fundraiser-detail.component.html'
})
export class FundraiserDetailComponent implements OnInit {
  fundraiser: IFundraiser;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fundraiser }) => {
      this.fundraiser = fundraiser;
    });
  }

  previousState() {
    window.history.back();
  }
}
