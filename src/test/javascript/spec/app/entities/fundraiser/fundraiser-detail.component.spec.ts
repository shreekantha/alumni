import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { FundraiserDetailComponent } from 'app/entities/fundraiser/fundraiser-detail.component';
import { Fundraiser } from 'app/shared/model/fundraiser.model';

describe('Component Tests', () => {
  describe('Fundraiser Management Detail Component', () => {
    let comp: FundraiserDetailComponent;
    let fixture: ComponentFixture<FundraiserDetailComponent>;
    const route = ({ data: of({ fundraiser: new Fundraiser('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [FundraiserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FundraiserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FundraiserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fundraiser).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
