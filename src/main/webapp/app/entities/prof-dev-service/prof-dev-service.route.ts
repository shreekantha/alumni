import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProfDevService, ProfDevService } from 'app/shared/model/prof-dev-service.model';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfDevServiceDeletePopupComponent } from './prof-dev-service-delete-dialog.component';
import { ProfDevServiceDetailComponent } from './prof-dev-service-detail.component';
import { ProfDevServiceUpdateComponent } from './prof-dev-service-update.component';
import { ProfDevServiceComponent } from './prof-dev-service.component';
import { ProfDevServiceService } from './prof-dev-service.service';

@Injectable({ providedIn: 'root' })
export class ProfDevServiceResolve implements Resolve<IProfDevService> {
  constructor(private service: ProfDevServiceService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProfDevService> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((profDevService: HttpResponse<ProfDevService>) => profDevService.body));
    }
    return of(new ProfDevService());
  }
}

export const profDevServiceRoute: Routes = [
  {
    path: '',
    component: ProfDevServiceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ProfDevServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProfDevServiceDetailComponent,
    resolve: {
      profDevService: ProfDevServiceResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'ProfDevServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProfDevServiceUpdateComponent,
    resolve: {
      profDevService: ProfDevServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ProfDevServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProfDevServiceUpdateComponent,
    resolve: {
      profDevService: ProfDevServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ProfDevServices'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const profDevServicePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProfDevServiceDeletePopupComponent,
    resolve: {
      profDevService: ProfDevServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ProfDevServices'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
