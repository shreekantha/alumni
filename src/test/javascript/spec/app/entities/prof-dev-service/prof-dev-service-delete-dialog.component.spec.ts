import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { ProfDevServiceDeleteDialogComponent } from 'app/entities/prof-dev-service/prof-dev-service-delete-dialog.component';
import { ProfDevServiceService } from 'app/entities/prof-dev-service/prof-dev-service.service';

describe('Component Tests', () => {
  describe('ProfDevService Management Delete Component', () => {
    let comp: ProfDevServiceDeleteDialogComponent;
    let fixture: ComponentFixture<ProfDevServiceDeleteDialogComponent>;
    let service: ProfDevServiceService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [ProfDevServiceDeleteDialogComponent]
      })
        .overrideTemplate(ProfDevServiceDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProfDevServiceDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProfDevServiceService);
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
