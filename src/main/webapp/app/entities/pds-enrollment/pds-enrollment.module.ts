import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { PdsEnrollmentComponent } from './pds-enrollment.component';
import { PdsEnrollmentDetailComponent } from './pds-enrollment-detail.component';
import { PdsEnrollmentUpdateComponent } from './pds-enrollment-update.component';
import { PdsEnrollmentDeletePopupComponent, PdsEnrollmentDeleteDialogComponent } from './pds-enrollment-delete-dialog.component';
import { pdsEnrollmentRoute, pdsEnrollmentPopupRoute } from './pds-enrollment.route';

const ENTITY_STATES = [...pdsEnrollmentRoute, ...pdsEnrollmentPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PdsEnrollmentComponent,
    PdsEnrollmentDetailComponent,
    PdsEnrollmentUpdateComponent,
    PdsEnrollmentDeleteDialogComponent,
    PdsEnrollmentDeletePopupComponent
  ],
  entryComponents: [PdsEnrollmentDeleteDialogComponent]
})
export class DhiAlumniPdsEnrollmentModule {}
