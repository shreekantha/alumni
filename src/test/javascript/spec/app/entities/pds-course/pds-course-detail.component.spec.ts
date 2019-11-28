import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsCourseDetailComponent } from 'app/entities/pds-course/pds-course-detail.component';
import { PdsCourse } from 'app/shared/model/pds-course.model';

describe('Component Tests', () => {
  describe('PdsCourse Management Detail Component', () => {
    let comp: PdsCourseDetailComponent;
    let fixture: ComponentFixture<PdsCourseDetailComponent>;
    const route = ({ data: of({ pdsCourse: new PdsCourse('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsCourseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PdsCourseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdsCourseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pdsCourse).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
