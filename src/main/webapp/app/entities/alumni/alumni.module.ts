import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { AlumniComponent } from './alumni.component';
import { AlumniDetailComponent } from './alumni-detail.component';
import { AlumniUpdateComponent } from './alumni-update.component';
import { AlumniDeletePopupComponent, AlumniDeleteDialogComponent } from './alumni-delete-dialog.component';
import { alumniRoute, alumniPopupRoute } from './alumni.route';

const ENTITY_STATES = [...alumniRoute, ...alumniPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [AlumniComponent, AlumniDetailComponent, AlumniUpdateComponent, AlumniDeleteDialogComponent, AlumniDeletePopupComponent],
  entryComponents: [AlumniDeleteDialogComponent]
})
export class DhiAlumniAlumniModule {}
