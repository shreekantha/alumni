import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PdsScheduleService } from 'app/entities/pds-schedule/pds-schedule.service';
import { IPdsSchedule, PdsSchedule } from 'app/shared/model/pds-schedule.model';

describe('Service Tests', () => {
  describe('PdsSchedule Service', () => {
    let injector: TestBed;
    let service: PdsScheduleService;
    let httpMock: HttpTestingController;
    let elemDefault: IPdsSchedule;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PdsScheduleService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PdsSchedule('ID', 0, currentDate, currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_FORMAT),
            time: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find('123')
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a PdsSchedule', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            date: currentDate.format(DATE_FORMAT),
            time: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            date: currentDate,
            time: currentDate
          },
          returnedFromService
        );
        service
          .create(new PdsSchedule(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a PdsSchedule', () => {
        const returnedFromService = Object.assign(
          {
            duration: 'BBBBBB',
            date: currentDate.format(DATE_FORMAT),
            time: currentDate.format(DATE_TIME_FORMAT),
            venue: 'BBBBBB',
            remarks: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
            time: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of PdsSchedule', () => {
        const returnedFromService = Object.assign(
          {
            duration: 'BBBBBB',
            date: currentDate.format(DATE_FORMAT),
            time: currentDate.format(DATE_TIME_FORMAT),
            venue: 'BBBBBB',
            remarks: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            date: currentDate,
            time: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a PdsSchedule', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
