import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';

@Component({
  selector: 'jhi-alumni-meet-req-detail',
  templateUrl: './alumni-meet-req-detail.component.html'
})
export class AlumniMeetReqDetailComponent implements OnInit {
  alumniMeetReq: IAlumniMeetReq;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alumniMeetReq }) => {
      this.alumniMeetReq = alumniMeetReq;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
