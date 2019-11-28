import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { DegreeDetailComponent } from 'app/entities/degree/degree-detail.component';
import { Degree } from 'app/shared/model/degree.model';

describe('Component Tests', () => {
  describe('Degree Management Detail Component', () => {
    let comp: DegreeDetailComponent;
    let fixture: ComponentFixture<DegreeDetailComponent>;
    const route = ({ data: of({ degree: new Degree('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [DegreeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DegreeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DegreeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.degree).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
