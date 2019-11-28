import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlumni } from 'app/shared/model/alumni.model';

@Component({
  selector: 'jhi-alumni-detail',
  templateUrl: './alumni-detail.component.html'
})
export class AlumniDetailComponent implements OnInit {
  alumni: IAlumni;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alumni }) => {
      this.alumni = alumni;
    });
  }

  previousState() {
    window.history.back();
  }
}
