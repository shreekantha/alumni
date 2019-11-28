import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsScheduleUpdateComponent } from 'app/entities/pds-schedule/pds-schedule-update.component';
import { PdsScheduleService } from 'app/entities/pds-schedule/pds-schedule.service';
import { PdsSchedule } from 'app/shared/model/pds-schedule.model';

describe('Component Tests', () => {
  describe('PdsSchedule Management Update Component', () => {
    let comp: PdsScheduleUpdateComponent;
    let fixture: ComponentFixture<PdsScheduleUpdateComponent>;
    let service: PdsScheduleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsScheduleUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PdsScheduleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PdsScheduleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdsScheduleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PdsSchedule('123');
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
        const entity = new PdsSchedule();
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
