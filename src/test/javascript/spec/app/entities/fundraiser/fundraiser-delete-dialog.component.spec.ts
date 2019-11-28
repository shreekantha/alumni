import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { FundraiserDeleteDialogComponent } from 'app/entities/fundraiser/fundraiser-delete-dialog.component';
import { FundraiserService } from 'app/entities/fundraiser/fundraiser.service';

describe('Component Tests', () => {
  describe('Fundraiser Management Delete Component', () => {
    let comp: FundraiserDeleteDialogComponent;
    let fixture: ComponentFixture<FundraiserDeleteDialogComponent>;
    let service: FundraiserService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [FundraiserDeleteDialogComponent]
      })
        .overrideTemplate(FundraiserDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FundraiserDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FundraiserService);
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
