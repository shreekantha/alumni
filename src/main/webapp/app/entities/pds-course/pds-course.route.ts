import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PdsCourse } from 'app/shared/model/pds-course.model';
import { PdsCourseService } from './pds-course.service';
import { PdsCourseComponent } from './pds-course.component';
import { PdsCourseDetailComponent } from './pds-course-detail.component';
import { PdsCourseUpdateComponent } from './pds-course-update.component';
import { PdsCourseDeletePopupComponent } from './pds-course-delete-dialog.component';
import { IPdsCourse } from 'app/shared/model/pds-course.model';

@Injectable({ providedIn: 'root' })
export class PdsCourseResolve implements Resolve<IPdsCourse> {
  constructor(private service: PdsCourseService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPdsCourse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((pdsCourse: HttpResponse<PdsCourse>) => pdsCourse.body));
    }
    return of(new PdsCourse());
  }
}

export const pdsCourseRoute: Routes = [
  {
    path: '',
    component: PdsCourseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'PdsCourses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PdsCourseDetailComponent,
    resolve: {
      pdsCourse: PdsCourseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PdsCourses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PdsCourseUpdateComponent,
    resolve: {
      pdsCourse: PdsCourseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PdsCourses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PdsCourseUpdateComponent,
    resolve: {
      pdsCourse: PdsCourseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PdsCourses'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const pdsCoursePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PdsCourseDeletePopupComponent,
    resolve: {
      pdsCourse: PdsCourseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PdsCourses'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
