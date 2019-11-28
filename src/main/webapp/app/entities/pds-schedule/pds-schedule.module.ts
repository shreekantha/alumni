import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { PdsScheduleComponent } from './pds-schedule.component';
import { PdsScheduleDetailComponent } from './pds-schedule-detail.component';
import { PdsScheduleUpdateComponent } from './pds-schedule-update.component';
import { PdsScheduleDeletePopupComponent, PdsScheduleDeleteDialogComponent } from './pds-schedule-delete-dialog.component';
import { pdsScheduleRoute, pdsSchedulePopupRoute } from './pds-schedule.route';

const ENTITY_STATES = [...pdsScheduleRoute, ...pdsSchedulePopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PdsScheduleComponent,
    PdsScheduleDetailComponent,
    PdsScheduleUpdateComponent,
    PdsScheduleDeleteDialogComponent,
    PdsScheduleDeletePopupComponent
  ],
  entryComponents: [PdsScheduleDeleteDialogComponent]
})
export class DhiAlumniPdsScheduleModule {}
