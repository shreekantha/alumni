import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPdsCourse } from 'app/shared/model/pds-course.model';

@Component({
  selector: 'jhi-pds-course-detail',
  templateUrl: './pds-course-detail.component.html'
})
export class PdsCourseDetailComponent implements OnInit {
  pdsCourse: IPdsCourse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pdsCourse }) => {
      this.pdsCourse = pdsCourse;
    });
  }

  previousState() {
    window.history.back();
  }
}
