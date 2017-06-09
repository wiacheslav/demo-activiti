import { DemoActivitiPage } from './app.po';

describe('demo-activiti App', () => {
  let page: DemoActivitiPage;

  beforeEach(() => {
    page = new DemoActivitiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
