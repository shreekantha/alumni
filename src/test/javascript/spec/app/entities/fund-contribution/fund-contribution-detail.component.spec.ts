import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { FundContributionDetailComponent } from 'app/entities/fund-contribution/fund-contribution-detail.component';
import { FundContribution } from 'app/shared/model/fund-contribution.model';

describe('Component Tests', () => {
  describe('FundContribution Management Detail Component', () => {
    let comp: FundContributionDetailComponent;
    let fixture: ComponentFixture<FundContributionDetailComponent>;
    const route = ({ data: of({ fundContribution: new FundContribution('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [FundContributionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FundContributionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FundContributionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fundContribution).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
