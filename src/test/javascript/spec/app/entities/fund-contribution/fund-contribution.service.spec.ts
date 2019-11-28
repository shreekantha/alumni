import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FundContributionService } from 'app/entities/fund-contribution/fund-contribution.service';
import { IFundContribution, FundContribution } from 'app/shared/model/fund-contribution.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

describe('Service Tests', () => {
  describe('FundContribution Service', () => {
    let injector: TestBed;
    let service: FundContributionService;
    let httpMock: HttpTestingController;
    let elemDefault: IFundContribution;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(FundContributionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FundContribution('ID', Currency.INR, 0, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            contrDate: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a FundContribution', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            contrDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            contrDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new FundContribution(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a FundContribution', () => {
        const returnedFromService = Object.assign(
          {
            currency: 'BBBBBB',
            contrAmount: 1,
            contrDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            contrDate: currentDate
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

      it('should return a list of FundContribution', () => {
        const returnedFromService = Object.assign(
          {
            currency: 'BBBBBB',
            contrAmount: 1,
            contrDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            contrDate: currentDate
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

      it('should delete a FundContribution', () => {
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
