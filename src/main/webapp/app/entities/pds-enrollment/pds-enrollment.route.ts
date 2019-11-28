import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPdsEnrollment, PdsEnrollment } from 'app/shared/model/pds-enrollment.model';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PdsEnrollmentDeletePopupComponent } from './pds-enrollment-delete-dialog.component';
import { PdsEnrollmentDetailComponent } from './pds-enrollment-detail.component';
import { PdsEnrollmentUpdateComponent } from './pds-enrollment-update.component';
import { PdsEnrollmentComponent } from './pds-enrollment.component';
import { PdsEnrollmentService } from './pds-enrollment.service';

@Injectable({ providedIn: 'root' })
export class PdsEnrollmentResolve implements Resolve<IPdsEnrollment> {
  constructor(private service: PdsEnrollmentService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPdsEnrollment> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((pdsEnrollment: HttpResponse<PdsEnrollment>) => pdsEnrollment.body));
    }
    return of(new PdsEnrollment());
  }
}

export const pdsEnrollmentRoute: Routes = [
  {
    path: '',
    component: PdsEnrollmentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      defaultSort: 'id,asc',
      pageTitle: 'PdsEnrollments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PdsEnrollmentDetailComponent,
    resolve: {
      pdsEnrollment: PdsEnrollmentResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'PdsEnrollments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PdsEnrollmentUpdateComponent,
    resolve: {
      pdsEnrollment: PdsEnrollmentResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'PdsEnrollments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PdsEnrollmentUpdateComponent,
    resolve: {
      pdsEnrollment: PdsEnrollmentResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'PdsEnrollments'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const pdsEnrollmentPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PdsEnrollmentDeletePopupComponent,
    resolve: {
      pdsEnrollment: PdsEnrollmentResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'PdsEnrollments'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
