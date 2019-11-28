import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { DegreeComponent } from './degree.component';
import { DegreeDetailComponent } from './degree-detail.component';
import { DegreeUpdateComponent } from './degree-update.component';
import { DegreeDeletePopupComponent, DegreeDeleteDialogComponent } from './degree-delete-dialog.component';
import { degreeRoute, degreePopupRoute } from './degree.route';

const ENTITY_STATES = [...degreeRoute, ...degreePopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [DegreeComponent, DegreeDetailComponent, DegreeUpdateComponent, DegreeDeleteDialogComponent, DegreeDeletePopupComponent],
  entryComponents: [DegreeDeleteDialogComponent]
})
export class DhiAlumniDegreeModule {}
