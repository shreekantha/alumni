import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFundContribution } from 'app/shared/model/fund-contribution.model';

@Component({
  selector: 'jhi-fund-contribution-detail',
  templateUrl: './fund-contribution-detail.component.html'
})
export class FundContributionDetailComponent implements OnInit {
  fundContribution: IFundContribution;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fundContribution }) => {
      this.fundContribution = fundContribution;
    });
  }

  previousState() {
    window.history.back();
  }
}
