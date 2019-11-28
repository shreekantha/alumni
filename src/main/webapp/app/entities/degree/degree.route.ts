import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Degree } from 'app/shared/model/degree.model';
import { DegreeService } from './degree.service';
import { DegreeComponent } from './degree.component';
import { DegreeDetailComponent } from './degree-detail.component';
import { DegreeUpdateComponent } from './degree-update.component';
import { DegreeDeletePopupComponent } from './degree-delete-dialog.component';
import { IDegree } from 'app/shared/model/degree.model';

@Injectable({ providedIn: 'root' })
export class DegreeResolve implements Resolve<IDegree> {
  constructor(private service: DegreeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDegree> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((degree: HttpResponse<Degree>) => degree.body));
    }
    return of(new Degree());
  }
}

export const degreeRoute: Routes = [
  {
    path: '',
    component: DegreeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Degrees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DegreeDetailComponent,
    resolve: {
      degree: DegreeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Degrees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DegreeUpdateComponent,
    resolve: {
      degree: DegreeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Degrees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DegreeUpdateComponent,
    resolve: {
      degree: DegreeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Degrees'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const degreePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DegreeDeletePopupComponent,
    resolve: {
      degree: DegreeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Degrees'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
