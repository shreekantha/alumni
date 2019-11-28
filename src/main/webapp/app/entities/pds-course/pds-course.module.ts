import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { PdsCourseComponent } from './pds-course.component';
import { PdsCourseDetailComponent } from './pds-course-detail.component';
import { PdsCourseUpdateComponent } from './pds-course-update.component';
import { PdsCourseDeletePopupComponent, PdsCourseDeleteDialogComponent } from './pds-course-delete-dialog.component';
import { pdsCourseRoute, pdsCoursePopupRoute } from './pds-course.route';

const ENTITY_STATES = [...pdsCourseRoute, ...pdsCoursePopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PdsCourseComponent,
    PdsCourseDetailComponent,
    PdsCourseUpdateComponent,
    PdsCourseDeleteDialogComponent,
    PdsCourseDeletePopupComponent
  ],
  entryComponents: [PdsCourseDeleteDialogComponent]
})
export class DhiAlumniPdsCourseModule {}
