from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
from bs4 import BeautifulSoup
import re
import os
options = webdriver.ChromeOptions()
options.binary_location = "D:\Google\Chrome\Application\chrome.exe"
# 启动浏览器
driver = webdriver.Chrome(executable_path="D:\\envs\\chrome\\chromedriver.exe",options=options)
url="https://app.mokahr.com/social-recruitment/kuro/46886#/home"
driver.get(url)
str="0898317247"
# 等待特定元素加载完成，表示页面已渲染
wait = WebDriverWait(driver, 60)

# all_elements = driver.find_elements(By.XPATH, str)
wait.until(EC.presence_of_element_located((By.ID, str)),message="666")
elements = driver.find_elements(By.ID,str)
page_text=driver.page_source
soup=BeautifulSoup(page_text,'lxml')
selectStr="#\\30 898317247 > div > div > a"
types=soup.select(selectStr)
id=1
p =f'./types.txt'
        # 检查并创建目录
d = os.path.dirname(p)
if not os.path.exists(d):
    os.makedirs(d)
for type in types:
    content=type.select("div > div > span")
    name=content[0].text
    number=re.search(r'\d+',content[1].previous).group(0)
    with open(p,'a') as fp:
        fp.write(f"{name},{number},{id}\n")
        id+=1
id=1
# 获取每个职位类型的列表链接
for type in types:
    href=type.get('href')
    baseurl="https://app.mokahr.com/social-recruitment/kuro/46886"
    newUrl=baseurl+href
    driver.get(newUrl)
    wait.until(EC.presence_of_all_elements_located((By.CLASS_NAME,"results-B3yJdKsHBN")))
    pText=driver.page_source
    s=BeautifulSoup(pText,'lxml')
    order=1
    hrs=s.select("#\\31 461685889 > div.jobs-list-WmE84RgZxp.large-kHhnUTb4Jc > div.results-B3yJdKsHBN > div.jobs-AkItzswt6b > div > a")
    # 从中获取了链接
    for hr in hrs:
        f=hr.get('href')
        newnewUrl=baseurl+f
        # 导航至对应链接
        driver.get(newnewUrl)
        # 等待对dom的监控结束
        wait.until(EC.presence_of_all_elements_located((By.CLASS_NAME,"job-description-VvfEUGocNE")))
        nText=driver.page_source 
        # 处理信息
        s2=BeautifulSoup(nText,'lxml')
        # 获取职位信息
        name=s2.select("#app > div > div > div > div.container-BUvzVDsAku > div > div > div > div > div > div > div > div > div > div.sd-Spacing-spacing-inline-3U1Fq.sd-Spacing-stretched-2oo1D.sd-Spacing-align-start-1RB7L.sd-Spacing-justify-between-SaguN > div.sd-Spacing-spacing-inline-3U1Fq.sd-Spacing-align-center-WxVXr > div")[0].text
        # 职位发布时间
        times=s2.select("#app > div > div > div > div.container-BUvzVDsAku > div > div > div > div > div > div > div > div > div > div.sd-Spacing-spacing-inline-3U1Fq.sd-Spacing-stretched-2oo1D.sd-Spacing-align-center-WxVXr.sd-Spacing-justify-between-SaguN > div.sd-Spacing-spacing-inline-3U1Fq.sd-Spacing-flex-vertical-3ZuOY > div.sd-Spacing-spacing-inline-3U1Fq.sd-Spacing-align-center-WxVXr.sd-foundation-body-tertiary-24ex4 > span")
        # 工作地点
        spaces=s2.select("#app > div > div > div > div.container-BUvzVDsAku > div > div > div > div > div > div > div:nth-child(1) > div > div > div.sd-Spacing-spacing-inline-3U1Fq.sd-Spacing-stretched-2oo1D.sd-Spacing-align-center-WxVXr.sd-Spacing-justify-between-SaguN > div.sd-Spacing-spacing-inline-3U1Fq.sd-Spacing-flex-vertical-3ZuOY > div.info-UcB_mxJq8y > span")[0].string
        te=times[0].string
        fff=re.search(r"\d{4}-\d{2}-\d{2}",te)
        # 工作内容
        contents=s2.select("#clamp-container > div >p")
        
        file_path =f'./positions/{id}/{order}.txt'
        # 检查并创建目录
        directory = os.path.dirname(file_path)
        if not os.path.exists(directory):
            os.makedirs(directory)
        with open(file_path,'a') as fp:
            # 按照格式写入数据
            fp.write("----\n")
            fp.write(name+"\n")
            fp.write("----\n")
            fp.write(fff.group(0)+"\n")
            fp.write("----\n")
            fp.write(spaces+"\n")
            fp.write("----\n")
            for content in contents:
                c=content.string
                if c is not None:
                    fp.write(c+"\n")
            order+=1
    id+=1

# 关闭浏览器
driver.quit()