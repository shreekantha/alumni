import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { AlumniMeetReqUpdateComponent } from 'app/entities/alumni-meet-req/alumni-meet-req-update.component';
import { AlumniMeetReqService } from 'app/entities/alumni-meet-req/alumni-meet-req.service';
import { AlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';

describe('Component Tests', () => {
  describe('AlumniMeetReq Management Update Component', () => {
    let comp: AlumniMeetReqUpdateComponent;
    let fixture: ComponentFixture<AlumniMeetReqUpdateComponent>;
    let service: AlumniMeetReqService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AlumniMeetReqUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AlumniMeetReqUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlumniMeetReqUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlumniMeetReqService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AlumniMeetReq('123');
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
        const entity = new AlumniMeetReq();
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
