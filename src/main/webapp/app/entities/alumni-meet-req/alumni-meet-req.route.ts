import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { AlumniMeetReq, IAlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AlumniMeetReqDeletePopupComponent } from './alumni-meet-req-delete-dialog.component';
import { AlumniMeetReqDetailComponent } from './alumni-meet-req-detail.component';
import { AlumniMeetReqProcessComponent } from './alumni-meet-req-process.component';
import { AlumniMeetReqUpdateComponent } from './alumni-meet-req-update.component';
import { AlumniMeetReqComponent } from './alumni-meet-req.component';
import { AlumniMeetReqService } from './alumni-meet-req.service';

@Injectable({ providedIn: 'root' })
export class AlumniMeetReqResolve implements Resolve<IAlumniMeetReq> {
  constructor(private service: AlumniMeetReqService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAlumniMeetReq> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((alumniMeetReq: HttpResponse<AlumniMeetReq>) => alumniMeetReq.body));
    }
    return of(new AlumniMeetReq());
  }
}

export const alumniMeetReqRoute: Routes = [
  {
    path: '',
    component: AlumniMeetReqComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI','ROLE_STUDENT'],
      defaultSort: 'id,asc',
      pageTitle: 'AlumniMeetReqs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'process',
    component: AlumniMeetReqProcessComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI','ROLE_STUDENT'],
      defaultSort: 'id,asc',
      pageTitle: 'Process Alumni Meet Reqs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AlumniMeetReqDetailComponent,
    resolve: {
      alumniMeetReq: AlumniMeetReqResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI','ROLE_STUDENT'],
      pageTitle: 'AlumniMeetReqs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AlumniMeetReqUpdateComponent,
    resolve: {
      alumniMeetReq: AlumniMeetReqResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI','ROLE_STUDENT'],
      pageTitle: 'AlumniMeetReqs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AlumniMeetReqUpdateComponent,
    resolve: {
      alumniMeetReq: AlumniMeetReqResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI','ROLE_STUDENT'],
      pageTitle: 'AlumniMeetReqs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const alumniMeetReqPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AlumniMeetReqDeletePopupComponent,
    resolve: {
      alumniMeetReq: AlumniMeetReqResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI','ROLE_STUDENT'],
      pageTitle: 'AlumniMeetReqs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
