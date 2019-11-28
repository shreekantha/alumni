import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PdsCourseTopic } from 'app/shared/model/pds-course-topic.model';
import { PdsCourseTopicService } from './pds-course-topic.service';
import { PdsCourseTopicComponent } from './pds-course-topic.component';
import { PdsCourseTopicDetailComponent } from './pds-course-topic-detail.component';
import { PdsCourseTopicUpdateComponent } from './pds-course-topic-update.component';
import { PdsCourseTopicDeletePopupComponent } from './pds-course-topic-delete-dialog.component';
import { IPdsCourseTopic } from 'app/shared/model/pds-course-topic.model';

@Injectable({ providedIn: 'root' })
export class PdsCourseTopicResolve implements Resolve<IPdsCourseTopic> {
  constructor(private service: PdsCourseTopicService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPdsCourseTopic> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((pdsCourseTopic: HttpResponse<PdsCourseTopic>) => pdsCourseTopic.body));
    }
    return of(new PdsCourseTopic());
  }
}

export const pdsCourseTopicRoute: Routes = [
  {
    path: '',
    component: PdsCourseTopicComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'PdsCourseTopics'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PdsCourseTopicDetailComponent,
    resolve: {
      pdsCourseTopic: PdsCourseTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PdsCourseTopics'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PdsCourseTopicUpdateComponent,
    resolve: {
      pdsCourseTopic: PdsCourseTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PdsCourseTopics'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PdsCourseTopicUpdateComponent,
    resolve: {
      pdsCourseTopic: PdsCourseTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PdsCourseTopics'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const pdsCourseTopicPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PdsCourseTopicDeletePopupComponent,
    resolve: {
      pdsCourseTopic: PdsCourseTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PdsCourseTopics'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
