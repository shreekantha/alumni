import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfDevService } from 'app/shared/model/prof-dev-service.model';

@Component({
  selector: 'jhi-prof-dev-service-detail',
  templateUrl: './prof-dev-service-detail.component.html'
})
export class ProfDevServiceDetailComponent implements OnInit {
  profDevService: IProfDevService;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ profDevService }) => {
      this.profDevService = profDevService;
    });
  }

  previousState() {
    window.history.back();
  }
}
