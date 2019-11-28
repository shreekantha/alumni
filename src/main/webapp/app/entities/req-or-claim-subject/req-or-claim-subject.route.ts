import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';
import { ReqOrClaimSubjectService } from './req-or-claim-subject.service';
import { ReqOrClaimSubjectComponent } from './req-or-claim-subject.component';
import { ReqOrClaimSubjectDetailComponent } from './req-or-claim-subject-detail.component';
import { ReqOrClaimSubjectUpdateComponent } from './req-or-claim-subject-update.component';
import { ReqOrClaimSubjectDeletePopupComponent } from './req-or-claim-subject-delete-dialog.component';
import { IReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';

@Injectable({ providedIn: 'root' })
export class ReqOrClaimSubjectResolve implements Resolve<IReqOrClaimSubject> {
  constructor(private service: ReqOrClaimSubjectService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReqOrClaimSubject> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((reqOrClaimSubject: HttpResponse<ReqOrClaimSubject>) => reqOrClaimSubject.body));
    }
    return of(new ReqOrClaimSubject());
  }
}

export const reqOrClaimSubjectRoute: Routes = [
  {
    path: '',
    component: ReqOrClaimSubjectComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ReqOrClaimSubjects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ReqOrClaimSubjectDetailComponent,
    resolve: {
      reqOrClaimSubject: ReqOrClaimSubjectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ReqOrClaimSubjects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ReqOrClaimSubjectUpdateComponent,
    resolve: {
      reqOrClaimSubject: ReqOrClaimSubjectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ReqOrClaimSubjects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ReqOrClaimSubjectUpdateComponent,
    resolve: {
      reqOrClaimSubject: ReqOrClaimSubjectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ReqOrClaimSubjects'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const reqOrClaimSubjectPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ReqOrClaimSubjectDeletePopupComponent,
    resolve: {
      reqOrClaimSubject: ReqOrClaimSubjectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ReqOrClaimSubjects'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
