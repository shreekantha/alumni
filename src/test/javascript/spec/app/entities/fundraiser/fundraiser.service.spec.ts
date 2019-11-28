import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { FundraiserService } from 'app/entities/fundraiser/fundraiser.service';
import { IFundraiser, Fundraiser } from 'app/shared/model/fundraiser.model';
import { FundraiseStatus } from 'app/shared/model/enumerations/fundraise-status.model';

describe('Service Tests', () => {
  describe('Fundraiser Service', () => {
    let injector: TestBed;
    let service: FundraiserService;
    let httpMock: HttpTestingController;
    let elemDefault: IFundraiser;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(FundraiserService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Fundraiser('ID', 'AAAAAAA', 'AAAAAAA', 0, 0, currentDate, FundraiseStatus.ACHIEVED);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            lastDateToContribute: currentDate.format(DATE_FORMAT)
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

      it('should create a Fundraiser', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            lastDateToContribute: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lastDateToContribute: currentDate
          },
          returnedFromService
        );
        service
          .create(new Fundraiser(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Fundraiser', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            description: 'BBBBBB',
            targetAmount: 1,
            collectedAmount: 1,
            lastDateToContribute: currentDate.format(DATE_FORMAT),
            status: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            lastDateToContribute: currentDate
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

      it('should return a list of Fundraiser', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            description: 'BBBBBB',
            targetAmount: 1,
            collectedAmount: 1,
            lastDateToContribute: currentDate.format(DATE_FORMAT),
            status: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lastDateToContribute: currentDate
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

      it('should delete a Fundraiser', () => {
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
