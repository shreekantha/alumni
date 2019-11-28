import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { AlumniReqOrClaimUpdateComponent } from 'app/entities/alumni-req-or-claim/alumni-req-or-claim-update.component';
import { AlumniReqOrClaimService } from 'app/entities/alumni-req-or-claim/alumni-req-or-claim.service';
import { AlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';

describe('Component Tests', () => {
  describe('AlumniReqOrClaim Management Update Component', () => {
    let comp: AlumniReqOrClaimUpdateComponent;
    let fixture: ComponentFixture<AlumniReqOrClaimUpdateComponent>;
    let service: AlumniReqOrClaimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AlumniReqOrClaimUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AlumniReqOrClaimUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlumniReqOrClaimUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlumniReqOrClaimService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AlumniReqOrClaim('123');
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
        const entity = new AlumniReqOrClaim();
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
