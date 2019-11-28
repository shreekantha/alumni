import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAspiredRole } from 'app/shared/model/aspired-role.model';

@Component({
  selector: 'jhi-aspired-role-detail',
  templateUrl: './aspired-role-detail.component.html'
})
export class AspiredRoleDetailComponent implements OnInit {
  aspiredRole: IAspiredRole;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ aspiredRole }) => {
      this.aspiredRole = aspiredRole;
    });
  }

  previousState() {
    window.history.back();
  }
}
