import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { FundraiserUpdateComponent } from 'app/entities/fundraiser/fundraiser-update.component';
import { FundraiserService } from 'app/entities/fundraiser/fundraiser.service';
import { Fundraiser } from 'app/shared/model/fundraiser.model';

describe('Component Tests', () => {
  describe('Fundraiser Management Update Component', () => {
    let comp: FundraiserUpdateComponent;
    let fixture: ComponentFixture<FundraiserUpdateComponent>;
    let service: FundraiserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [FundraiserUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FundraiserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FundraiserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FundraiserService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Fundraiser('123');
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
        const entity = new Fundraiser();
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
