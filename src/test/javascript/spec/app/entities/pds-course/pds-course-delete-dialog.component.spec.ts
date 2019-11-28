import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsCourseDeleteDialogComponent } from 'app/entities/pds-course/pds-course-delete-dialog.component';
import { PdsCourseService } from 'app/entities/pds-course/pds-course.service';

describe('Component Tests', () => {
  describe('PdsCourse Management Delete Component', () => {
    let comp: PdsCourseDeleteDialogComponent;
    let fixture: ComponentFixture<PdsCourseDeleteDialogComponent>;
    let service: PdsCourseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsCourseDeleteDialogComponent]
      })
        .overrideTemplate(PdsCourseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdsCourseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdsCourseService);
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
