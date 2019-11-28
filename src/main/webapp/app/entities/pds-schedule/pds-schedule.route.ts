import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPdsSchedule, PdsSchedule } from 'app/shared/model/pds-schedule.model';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PdsScheduleDeletePopupComponent } from './pds-schedule-delete-dialog.component';
import { PdsScheduleDetailComponent } from './pds-schedule-detail.component';
import { PdsScheduleUpdateComponent } from './pds-schedule-update.component';
import { PdsScheduleComponent } from './pds-schedule.component';
import { PdsScheduleService } from './pds-schedule.service';

@Injectable({ providedIn: 'root' })
export class PdsScheduleResolve implements Resolve<IPdsSchedule> {
  constructor(private service: PdsScheduleService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPdsSchedule> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((pdsSchedule: HttpResponse<PdsSchedule>) => pdsSchedule.body));
    }
    return of(new PdsSchedule());
  }
}

export const pdsScheduleRoute: Routes = [
  {
    path: '',
    component: PdsScheduleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      defaultSort: 'id,asc',
      pageTitle: 'PdsSchedules'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PdsScheduleDetailComponent,
    resolve: {
      pdsSchedule: PdsScheduleResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'PdsSchedules'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PdsScheduleUpdateComponent,
    resolve: {
      pdsSchedule: PdsScheduleResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'PdsSchedules'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PdsScheduleUpdateComponent,
    resolve: {
      pdsSchedule: PdsScheduleResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'PdsSchedules'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const pdsSchedulePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PdsScheduleDeletePopupComponent,
    resolve: {
      pdsSchedule: PdsScheduleResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'PdsSchedules'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
