import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsEnrollmentUpdateComponent } from 'app/entities/pds-enrollment/pds-enrollment-update.component';
import { PdsEnrollmentService } from 'app/entities/pds-enrollment/pds-enrollment.service';
import { PdsEnrollment } from 'app/shared/model/pds-enrollment.model';

describe('Component Tests', () => {
  describe('PdsEnrollment Management Update Component', () => {
    let comp: PdsEnrollmentUpdateComponent;
    let fixture: ComponentFixture<PdsEnrollmentUpdateComponent>;
    let service: PdsEnrollmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsEnrollmentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PdsEnrollmentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PdsEnrollmentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdsEnrollmentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PdsEnrollment('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PdsEnrollment();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
