import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'prof-dev-service',
        loadChildren: () => import('./prof-dev-service/prof-dev-service.module').then(m => m.DhiAlumniProfDevServiceModule)
      },
      {
        path: 'pds-course',
        loadChildren: () => import('./pds-course/pds-course.module').then(m => m.DhiAlumniPdsCourseModule)
      },
      {
        path: 'pds-course-topic',
        loadChildren: () => import('./pds-course-topic/pds-course-topic.module').then(m => m.DhiAlumniPdsCourseTopicModule)
      },
      {
        path: 'pds-schedule',
        loadChildren: () => import('./pds-schedule/pds-schedule.module').then(m => m.DhiAlumniPdsScheduleModule)
      },
      {
        path: 'fundraiser',
        loadChildren: () => import('./fundraiser/fundraiser.module').then(m => m.DhiAlumniFundraiserModule)
      },
      {
        path: 'fund-contribution',
        loadChildren: () => import('./fund-contribution/fund-contribution.module').then(m => m.DhiAlumniFundContributionModule)
      },
      {
        path: 'req-or-claim-subject',
        loadChildren: () => import('./req-or-claim-subject/req-or-claim-subject.module').then(m => m.DhiAlumniReqOrClaimSubjectModule)
      },
      {
        path: 'alumni-req-or-claim',
        loadChildren: () => import('./alumni-req-or-claim/alumni-req-or-claim.module').then(m => m.DhiAlumniAlumniReqOrClaimModule)
      },
      {
        path: 'alumni-meet-req',
        loadChildren: () => import('./alumni-meet-req/alumni-meet-req.module').then(m => m.DhiAlumniAlumniMeetReqModule)
      },
      {
        path: 'meet-req-topic',
        loadChildren: () => import('./meet-req-topic/meet-req-topic.module').then(m => m.DhiAlumniMeetReqTopicModule)
      },
      {
        path: 'aspired-role',
        loadChildren: () => import('./aspired-role/aspired-role.module').then(m => m.DhiAlumniAspiredRoleModule)
      },
      {
        path: 'pds-enrollment',
        loadChildren: () => import('./pds-enrollment/pds-enrollment.module').then(m => m.DhiAlumniPdsEnrollmentModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class DhiAlumniEntityModule {}
