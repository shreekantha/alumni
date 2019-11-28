import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { AspiredRoleDeleteDialogComponent } from 'app/entities/aspired-role/aspired-role-delete-dialog.component';
import { AspiredRoleService } from 'app/entities/aspired-role/aspired-role.service';

describe('Component Tests', () => {
  describe('AspiredRole Management Delete Component', () => {
    let comp: AspiredRoleDeleteDialogComponent;
    let fixture: ComponentFixture<AspiredRoleDeleteDialogComponent>;
    let service: AspiredRoleService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AspiredRoleDeleteDialogComponent]
      })
        .overrideTemplate(AspiredRoleDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AspiredRoleDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AspiredRoleService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
